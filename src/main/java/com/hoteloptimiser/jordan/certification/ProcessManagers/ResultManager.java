package com.hoteloptimiser.jordan.certification.ProcessManagers;

import com.hoteloptimiser.jordan.certification.Certification.Managers.DailyManager;
import com.hoteloptimiser.jordan.certification.Utils.JSONObjectCustom;
import com.hoteloptimiser.jordan.certification.Utils.PasteBinAPI;
import com.hoteloptimiser.jordan.certification.Utils.UrlRequester;
import lombok.*;
import okhttp3.MediaType;
import okhttp3.Request;
import okhttp3.RequestBody;
import org.json.JSONObject;
import org.json.XML;

import java.io.IOException;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Collectors;

@Data
public class ResultManager {

    private final Method method;

    private final Class<? extends CertificationListener> clazz;

    private String sendLink = "";

    private String resultLink = "";

    private String contentXml;

    private String inventoryXml;

    private Certification certification;

    private boolean success;

    private DailyManager manager;

    private String name;

    private int sleep;

    private String getContent(String xml) throws IOException {
        return Files.lines(Paths.get("src/main/resources/" + xml)).collect(Collectors.joining("\n"));
    }

    @SneakyThrows
    private CertificationListener instantiateClass() {
        val constructor = this.clazz.getConstructors()[0];
        return (CertificationListener)constructor.newInstance();
    }

    @SneakyThrows
    private boolean launchResult() {
        val listener = this.instantiateClass();
        return (boolean) method.invoke(listener, this.manager);
    }

    @SneakyThrows
    private String sendSpecXml(final String xml) {
        return UrlRequester.launchPostRequest("http://webservice.othyssia.dataloading.adapter.update.koedia.com/loadingservice",
                null, new UrlRequester.actionOnPost() {
                    @Override
                    public void action(Request.Builder build, RequestBody body) {
                        build.post(body);
                    }

                    @Override
                    public RequestBody getRequestBody() {
                        return RequestBody.create(MediaType.parse("text/xml;charset=UTF-8"), xml);
                    }
                });
    }

    private String replaceValues(String content) {
        return content
                .replaceAll("@login", System.getenv("xml_login"))
                .replaceAll("@pass", System.getenv("xml_password"))
                .replaceAll("@channel", System.getenv("xml_channel"))
                .replaceAll("@keystore", System.getenv("xml_key"))
                .replaceAll("@accom", System.getenv("xml_accom"));
    }

    @SneakyThrows
    private void getDailyUpdate() {
        this.inventoryXml = this.replaceValues(this.getContent(this.certification.inventory()));

        String answer = this.sendSpecXml(this.inventoryXml);

        JSONObjectCustom obj = new JSONObjectCustom(XML.toJSONObject(answer));

        obj = obj.getJSONObject("soap:Envelope").getJSONObject("soap:Body").getJSONObject("getInventoriesResponse").getJSONObject("InventoryResponse").getJSONObject("Accommodation").getJSONObject("DailyUpdates");

        this.manager = new DailyManager();
        this.manager.deserialize(obj);
    }

    public void sendXml() throws Exception {
        this.certification = this.method.getAnnotation(Certification.class);
        this.contentXml = this.replaceValues(this.getContent(this.certification.xml()));
        this.sleep = this.certification.sleep();
        this.sendLink = PasteBinAPI.newPasteBin("Certification ID : " + this.certification.id(), this.contentXml);

        String answer = this.sendSpecXml(this.contentXml);

        if (answer != null && answer.contains("success=\"true\""))
            this.resultLink = PasteBinAPI.newPasteBin("Result for certification ID : " + this.certification.id(), answer);
    }

    public void getResults() {
        this.name = this.method.getName();
        if (!this.certification.inventory().equalsIgnoreCase("")) {
            this.getDailyUpdate();
            this.success = this.launchResult();
        } else
            this.success = this.resultLink.contains("http");
    }

}
