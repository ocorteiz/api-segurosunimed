package com.example.api.convert;

public interface IConvertData {

    <T> T obterDados(String json, Class<T> classe);

}
