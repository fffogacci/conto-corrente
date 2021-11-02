package it.test.fabrick.contocorrente.controller;


import it.test.fabrick.contocorrente.model.Bonifico;
import org.json.JSONObject;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.HashMap;
import java.util.Map;

@org.springframework.web.bind.annotation.RestController
public class RestController {

    final String ACCOUNT_ID = "14537780";
    @GetMapping("/getLetturaSaldo")
    public String letturaSaldo() {
        String uri = "https://sandbox.platfr.io/api/gbs/banking/v4.0/accounts/" + ACCOUNT_ID + "/balance";
        RestTemplate restTemplate = new RestTemplate();

        HttpHeaders headers = new HttpHeaders();
        headers.set("Api-key", "FXOVVXXHVCPVPBZXIJOBGUGSKHDNFRRQJP");
        headers.set("Auth-Schema", "S2S");

        HttpEntity entity = new HttpEntity(headers);
        ResponseEntity<String> res = restTemplate.exchange(uri, HttpMethod.GET, entity, String.class);
        JSONObject jsonObject = new JSONObject(res.getBody());
        JSONObject payload = new JSONObject(jsonObject.get("payload").toString());
        return payload.get("balance").toString();
    }

    @GetMapping("/getLetturaTransazioni")
    public String letturaTransazioni() {
        String uri = "https://sandbox.platfr.io/api/gbs/banking/v4.0/accounts/" + ACCOUNT_ID + "/transactions";
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.set("Api-key", "FXOVVXXHVCPVPBZXIJOBGUGSKHDNFRRQJP");
        headers.set("Auth-Schema", "S2S");
        String urlTemplate = UriComponentsBuilder.fromHttpUrl(uri)
                .queryParam("fromAccountingDate", "{fromAccountingDate}")
                .queryParam("toAccountingDate", "{toAccountingDate}")
                .encode()
                .toUriString();
        Map<String, String> params = new HashMap<>();
        params.put("fromAccountingDate", "2019-01-01");
        params.put("toAccountingDate", "2019-12-01");
        HttpEntity entity = new HttpEntity(headers);
        ResponseEntity<String> res = restTemplate.exchange(urlTemplate, HttpMethod.GET, entity, String.class, params);
        JSONObject jsonObject = new JSONObject(res.getBody());
        return jsonObject.get("payload").toString();

    }

    @GetMapping("/getBonifico")
    public String bonifico(){
        String uri = "https://sandbox.platfr.io/api/gbs/banking/v4.0/accounts/" + ACCOUNT_ID + "/payments/money-transfers";
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.set("Api-key", "FXOVVXXHVCPVPBZXIJOBGUGSKHDNFRRQJP");
        headers.set("Auth-Schema", "S2S");
        headers.set("X-Time-Zone", "");

        Bonifico bonifico = new Bonifico(14537780L, "francesca", "bonifico", "euro", "123.5", "2021-11-02");

        HttpEntity<Bonifico> entity = new HttpEntity<Bonifico>(bonifico, headers);
        ResponseEntity<String> res = restTemplate.exchange(uri, HttpMethod.POST, entity, String.class);

        return  res.getBody();
    }
}
