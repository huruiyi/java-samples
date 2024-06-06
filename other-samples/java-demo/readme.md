```
datasource.password=DEC(test123)
jasypt.encryptor.password=password
```

```
用插件进行加密:jasypt:encrypt
mvn jasypt:encrypt -Djasypt.encryptor.password=password
```

```
datasource.password=ENC(NhLtSxa8LWYaANfr4hVkJIRMczlg5Owvj6VaR+LVpQKRVZvsGDQs2rr+XDLnol0F)
jasypt.encryptor.password=password
```

```
用插件进行解密:jasypt:decrypt
mvn jasypt:decrypt -Djasypt.encryptor.password=password
```

