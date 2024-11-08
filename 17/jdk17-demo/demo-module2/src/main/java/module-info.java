module roy.demoModule2 {
    exports com.roy.service;

    provides com.roy.service.HelloService with
            com.roy.service.impl.MorningHello,
            com.roy.service.impl.EveningHello;
}