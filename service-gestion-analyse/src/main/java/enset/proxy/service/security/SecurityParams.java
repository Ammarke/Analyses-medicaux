package enset.proxy.service.security;

public interface SecurityParams {
    public static final String JWT_HEADER_NAME="Authorization";
    public static final String JWT_SECRECT="ammar.kebiri@gmail.com";
    public static final long JWT_EXPIRATION= 864000;
    public static final String JWT_HEADER_PREFIX = "Bearer ";
}
