package com.mx.agenciamotos.Info;

import org.springframework.boot.actuate.info.Info;
import org.springframework.boot.actuate.info.InfoContributor;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class CustomInfoContributor implements InfoContributor {

    @Override
    public void contribute(Info.Builder builder) {
        Map<String, Object> customInfo = new HashMap<>();
        customInfo.put("Nombre de la aplicacion", "Agencia de motos 2");
        customInfo.put("Descripcion de la aplicacion", "Esta es mi aplicación Spring Boot refrente a motos");
         builder.withDetails(customInfo);
    }
}
