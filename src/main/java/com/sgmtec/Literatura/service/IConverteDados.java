package com.sgmtec.Literatura.service;

public interface IConverteDados {
    <T> T obterDados(String json, Class<T> classe);
}
