package it.test.fabrick.contocorrente.controller;


import org.json.JSONObject;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.client.RestTemplate;

@org.springframework.web.bind.annotation.RestController
public class RestController {

    @GetMapping("/getLetturaSaldo")
    public String letturaSaldo() {
        String uri = "https://sandbox.platfr.io/api/gbs/banking/v4.0/accounts/14537780/balance";
        RestTemplate restTemplate = new RestTemplate();

        HttpHeaders headers = new HttpHeaders();
        headers.set("Api-key", "FXOVVXXHVCPVPBZXIJOBGUGSKHDNFRRQJP");
        headers.set("Auth-Schema", "S2S");

        HttpEntity entity = new HttpEntity(headers);
        ResponseEntity<String> res = restTemplate.exchange(uri, HttpMethod.GET, entity, String.class);
        //String result = restTemplate.getForObject(uri, String.class);
        JSONObject jsonObject = new JSONObject(res.getBody());
        JSONObject payload = new JSONObject(jsonObject.get("payload").toString());
        return payload.get("balance").toString();


    }
}
