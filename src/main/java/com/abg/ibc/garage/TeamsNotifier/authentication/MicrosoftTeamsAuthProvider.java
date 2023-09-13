package com.abg.ibc.garage.TeamsNotifier.authentication;

import com.azure.identity.ClientCertificateCredential;
import com.azure.identity.ClientCertificateCredentialBuilder;
import com.azure.identity.ClientSecretCredential;
import com.azure.identity.ClientSecretCredentialBuilder;
import com.microsoft.graph.authentication.TokenCredentialAuthProvider;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class MicrosoftTeamsAuthProvider {

    @Value("${microsoft.client.id}")
    private String microsoftClientId;

    @Value("${microsoft.tenant.id}")
    private String microsoftTenantId;

    @Value("${microsoft.client.certificate.path}")
    private String microsoftClientCertificatePath;

    @Value("${microsoft.client.secret}")
    String clientSecret;

    @Bean
    public TokenCredentialAuthProvider getAuthProvider() throws Exception {
        final String clientId = microsoftClientId;
        final String tenantId = microsoftTenantId; // or "common" for multi-tenant apps
        final String clientCertificatePath  = microsoftClientCertificatePath;
        final List<String> scopes = List.of("https://graph.microsoft.com/.default");

        final ClientSecretCredential credential = new ClientSecretCredentialBuilder().clientId(clientId)
                .tenantId(tenantId)
                .clientSecret(clientSecret)
                .build();

//        final ClientCertificateCredential credential = new ClientCertificateCredentialBuilder()
//                .clientId(clientId).tenantId(tenantId).pemCertificate(clientCertificatePath)
//                .build();

        if (null == credential) {
            throw new Exception("Unexpected error");
        }
        return new TokenCredentialAuthProvider(
                scopes, credential);
    }

}
