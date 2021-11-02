package it.test.fabrick.contocorrente.model;

public class Bonifico {
    private Long accountId;
    private String ReceiveName;
    private String description;
    private String currency;
    private String amount;
    private String executionDate;

    public Bonifico() {
    }

    public Bonifico(Long accountId, String receiveName, String description, String currency, String amount, String executionDate) {
        this.accountId = accountId;
        ReceiveName = receiveName;
        this.description = description;
        this.currency = currency;
        this.amount = amount;
        this.executionDate = executionDate;
    }

    public Long getAccountId() {
        return accountId;
    }

    public void setAccountId(Long accountId) {
        this.accountId = accountId;
    }

    public String getReceiveName() {
        return ReceiveName;
    }

    public void setReceiveName(String receiveName) {
        ReceiveName = receiveName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getExecutionDate() {
        return executionDate;
    }

    public void setExecutionDate(String executionDate) {
        this.executionDate = executionDate;
    }

    @Override
    public String toString() {
        return "Bonifico{" +
                "accountId=" + accountId +
                ", ReceiveName='" + ReceiveName + '\'' +
                ", description='" + description + '\'' +
                ", currency='" + currency + '\'' +
                ", amount='" + amount + '\'' +
                ", executionDate='" + executionDate + '\'' +
                '}';
    }
}
