package net.serenitybdd.core.webdriver.driverproviders;

import net.serenitybdd.core.environment.EnvironmentSpecificConfiguration;
import net.thucydides.core.ThucydidesSystemProperty;
import net.thucydides.core.util.EnvironmentVariables;
import org.apache.commons.lang3.StringUtils;
import org.openqa.selenium.Proxy;

import java.util.Optional;

import static net.thucydides.core.ThucydidesSystemProperty.*;

public class ConfiguredProxy {

    public static Optional<Proxy> definedIn(EnvironmentVariables environmentVariables) {
        String httpProxy = EnvironmentSpecificConfiguration.from(environmentVariables).getProperty(SERENITY_PROXY_HTTP);
        String sslProxy = EnvironmentSpecificConfiguration.from(environmentVariables).getProperty(SERENITY_PROXY_SSL);
        String ftpProxy = EnvironmentSpecificConfiguration.from(environmentVariables).getProperty(SERENITY_PROXY_FTP);
        String noProxy = EnvironmentSpecificConfiguration.from(environmentVariables).getProperty(SERENITY_PROXY_NOPROXY);
        String proxyType = EnvironmentSpecificConfiguration.from(environmentVariables).getProperty(SERENITY_PROXY_TYPE);
        String proxyAutoconfigUrl = EnvironmentSpecificConfiguration.from(environmentVariables).getProperty(SERENITY_PROXY_AUTOCONFIG);
        String socksProxy = EnvironmentSpecificConfiguration.from(environmentVariables).getProperty(SERENITY_PROXY_SOCKS_PROXY);
        String socksProxyUsername = EnvironmentSpecificConfiguration.from(environmentVariables).getProperty(SERENITY_PROXY_SOCKS_USERNAME);
        String socksProxyPassword = EnvironmentSpecificConfiguration.from(environmentVariables).getProperty(SERENITY_PROXY_SOCKS_PASSWORD);
        String socksProxyVersionValue = EnvironmentSpecificConfiguration.from(environmentVariables).getProperty(SERENITY_PROXY_SOCKS_VERSION);
        Integer socksProxyVersion = (StringUtils.isNumeric(socksProxyVersionValue)) ? Integer.parseInt(socksProxyVersionValue) : null;

        Boolean autodetect = ThucydidesSystemProperty.SERENITY_PROXY_AUTODETECT.booleanFrom(environmentVariables, false);

        if ((httpProxy  != null) && (!httpProxy.isEmpty())) {
            org.openqa.selenium.Proxy proxy = new org.openqa.selenium.Proxy();
            if (httpProxy != null) {
                proxy.setHttpProxy(httpProxy);
            }
            if (sslProxy != null) {
                proxy.setSslProxy(sslProxy);
            }
            if (ftpProxy != null) {
                proxy.setFtpProxy(ftpProxy);
            }
            proxy.setAutodetect(autodetect);

            if (ftpProxy != null) {
                proxy.setFtpProxy(ftpProxy);
            }
            if (noProxy != null) {
                proxy.setNoProxy(noProxy);
            }
            if (proxyAutoconfigUrl != null) {
                proxy.setProxyAutoconfigUrl(proxyAutoconfigUrl);
            }
            if (proxyType != null) {
                proxy.setProxyType(org.openqa.selenium.Proxy.ProxyType.valueOf(proxyType));
            }
            if (socksProxy != null) {
                proxy.setSocksProxy(socksProxy);
            }
            if (socksProxyUsername != null) {
                proxy.setSocksUsername(socksProxyUsername);
            }
            if (socksProxyPassword != null) {
                proxy.setSocksPassword(socksProxyPassword);
            }
            if (socksProxyVersion != 0) {
                proxy.setSocksVersion(socksProxyVersion);
            }
            return Optional.of(proxy);
        }
        return Optional.empty();
    }
}
