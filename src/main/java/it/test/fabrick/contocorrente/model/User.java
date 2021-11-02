package it.test.fabrick.contocorrente.model;

public class User {
    private Long accountId;
    private String name;
    private String saldo;

    public User() {
    }

    public User(Long accountId, String name, String saldo) {
        this.accountId = accountId;
        this.name = name;
        this.saldo = saldo;
    }

    public Long getAccountId() {
        return accountId;
    }

    public void setAccountId(Long accountId) {
        this.accountId = accountId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSaldo() {
        return saldo;
    }

    public void setSaldo(String saldo) {
        this.saldo = saldo;
    }

    @Override
    public String toString() {
        return "User{" +
                "accountId=" + accountId +
                ", name='" + name + '\'' +
                ", saldo='" + saldo + '\'' +
                '}';
    }
}
