package com.example.groovy_config

import  com.example.groovy_config_java.model.Singer
import org.springframework.context.support.GenericApplicationContext
import org.springframework.beans.factory.groovy.GroovyBeanDefinitionReader

def ctx = new GenericApplicationContext()
def reader = new GroovyBeanDefinitionReader(ctx)

reader.beans {
    singer(Singer, name: 'John ', age: 39)
}

ctx.refresh()

println ctx.getBean("singer")
