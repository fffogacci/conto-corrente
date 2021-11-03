package it.test.fabrick.contocorrente.controller;


import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.HashMap;
import java.util.Map;

@org.springframework.web.bind.annotation.RestController
public class RestController {


    private final String ACCOUNT_ID = "14537780";
    private final String DOMAIN = "https://sandbox.platfr.io";
    private final String APIKEY = "FXOVVXXHVCPVPBZXIJOBGUGSKHDNFRRQJP";
    private final String S2S = "S2S";

    private HttpHeaders getHeader(){
        HttpHeaders headers = new HttpHeaders();
        headers.set("Api-key", APIKEY);
        headers.set("Auth-Schema", S2S);
        return headers;
    }

    @GetMapping("/getLetturaSaldo")
    public String letturaSaldo() {
        String uri = DOMAIN
                + "/api/gbs/banking/v4.0/accounts/"
                + ACCOUNT_ID
                + "/balance";
        RestTemplate restTemplate = new RestTemplate();

        HttpHeaders header = getHeader();
        HttpEntity entity = new HttpEntity(header);

        ResponseEntity<String> res = restTemplate.exchange(uri, HttpMethod.GET, entity, String.class);
        JSONObject jsonObject = new JSONObject(res.getBody());
        JSONObject payload = new JSONObject(jsonObject.get("payload").toString());
        return payload.get("balance").toString();
    }

    @GetMapping("/getLetturaTransazioni")
    public String letturaTransazioni() {
        String uri = DOMAIN
                + "/api/gbs/banking/v4.0/accounts/"
                + ACCOUNT_ID
                + "/transactions";
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders header = getHeader();
        HttpEntity entity = new HttpEntity(header);

        String urlTemplate = UriComponentsBuilder.fromHttpUrl(uri)
                .queryParam("fromAccountingDate", "{fromAccountingDate}")
                .queryParam("toAccountingDate", "{toAccountingDate}")
                .encode()
                .toUriString();
        Map<String, String> params = new HashMap<>();
        params.put("fromAccountingDate", "2019-01-01");
        params.put("toAccountingDate", "2019-12-01");

        ResponseEntity<String> res = restTemplate.exchange(urlTemplate, HttpMethod.GET, entity, String.class, params);
        JSONObject jsonObject = new JSONObject(res.getBody());
        return jsonObject.get("payload").toString();

    }

    @GetMapping("/getBonifico")
    public String bonifico(){
        String uri = DOMAIN
                + "/api/gbs/banking/v4.0/accounts/"
                + ACCOUNT_ID
                + "/payments/money-transfers";
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders header = getHeader();
        header.set("X-Time-Zone", "Europe/Rome");
        header.setContentType(MediaType.APPLICATION_JSON);


        String b = "{\n" +
                "  \"creditor\": {\n" +
                "    \"name\": \"John Doe\",\n" +
                "    \"account\": {\n" +
                "      \"accountCode\": \"IT23A0336844430152923804660\",\n" +
                "      \"bicCode\": \"SELBIT2BXXX\"\n" +
                "    },\n" +
                "    \"address\": {\n" +
                "      \"address\": null,\n" +
                "      \"city\": null,\n" +
                "      \"countryCode\": null\n" +
                "    }\n" +
                "  },\n" +
                "  \"executionDate\": \"2019-04-01\",\n" +
                "  \"uri\": \"REMITTANCE_INFORMATION\",\n" +
                "  \"description\": \"Payment invoice 75/2017\",\n" +
                "  \"amount\": 800,\n" +
                "  \"currency\": \"EUR\",\n" +
                "  \"isUrgent\": false,\n" +
                "  \"isInstant\": false,\n" +
                "  \"feeType\": \"SHA\",\n" +
                "  \"feeAccountId\": \"45685475\",\n" +
                "  \"taxRelief\": {\n" +
                "    \"taxReliefId\": \"L449\",\n" +
                "    \"isCondoUpgrade\": false,\n" +
                "    \"creditorFiscalCode\": \"56258745832\",\n" +
                "    \"beneficiaryType\": \"NATURAL_PERSON\",\n" +
                "    \"naturalPersonBeneficiary\": {\n" +
                "      \"fiscalCode1\": \"MRLFNC81L04A859L\",\n" +
                "      \"fiscalCode2\": null,\n" +
                "      \"fiscalCode3\": null,\n" +
                "      \"fiscalCode4\": null,\n" +
                "      \"fiscalCode5\": null\n" +
                "    },\n" +
                "    \"legalPersonBeneficiary\": {\n" +
                "      \"fiscalCode\": null,\n" +
                "      \"legalRepresentativeFiscalCode\": null\n" +
                "    }\n" +
                "  }\n" +
                "}";

        HttpEntity<String> entity = new HttpEntity<String>(b, header);

        ResponseEntity<String> res = restTemplate.exchange(uri, HttpMethod.POST, entity, String.class);

        return  res.getBody();
    }
}
