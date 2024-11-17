package com.example.security;

public class RsaKeyPair {

  private final String publicKey;
  private final String privateKey;

  public RsaKeyPair(String publicKey, String privateKey) {
    this.publicKey = publicKey;
    this.privateKey = privateKey;
  }

  public String getPublicKey() {
    return publicKey;
  }

  public String getPrivateKey() {
    return privateKey;
  }

}
