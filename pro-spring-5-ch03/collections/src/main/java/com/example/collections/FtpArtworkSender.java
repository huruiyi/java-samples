package com.example.collections;

public class FtpArtworkSender implements ArtworkSender {

  @Override
  public void sendArtwork(String artworkPath, Recipient recipient) {
    // ftp logic here...
  }

  @Override
  public String getFriendlyName() {
    return "File Transfer Protocol";
  }

  @Override
  public String getShortName() {
    return "ftp";
  }
}
