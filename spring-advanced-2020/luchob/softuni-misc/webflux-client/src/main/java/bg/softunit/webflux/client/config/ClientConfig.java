package bg.softunit.webflux.client.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableConfigurationProperties
@ConfigurationProperties("softuni.webflux.client")
public class ClientConfig {

  private String schema;
  private String host;
  private int port;

  public String getSchema() {
    return schema;
  }

  public ClientConfig setSchema(String schema) {
    this.schema = schema;
    return this;
  }

  public String getHost() {
    return host;
  }

  public ClientConfig setHost(String host) {
    this.host = host;
    return this;
  }

  public int getPort() {
    return port;
  }

  public ClientConfig setPort(int port) {
    this.port = port;
    return this;
  }

  @Override
  public String toString() {
    return "ClientConfig{" +
        "schema='" + schema + '\'' +
        ", host='" + host + '\'' +
        ", port=" + port +
        '}';
  }
}
