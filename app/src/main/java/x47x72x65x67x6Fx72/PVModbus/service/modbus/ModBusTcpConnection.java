package x47x72x65x67x6Fx72.PVModbus.service.modbus;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

/**
 * Manages TCP connection to server
 */
public class ModBusTcpConnection {
    private final String target;
    private final int port;
    private Socket socket;
    private InputStream inputStream;
    private OutputStream outputStream;


    /**
     * Standard Constructor
     *
     * @param target IP-Address e.g. 192.168.188.10
     * @param port Port (usually 502)
     */
    public ModBusTcpConnection(String target, int port) {
        this.target = target;
        this.port = port;
    }

    /**
     * Opens TCP-connection to server
     *
     * @return self
     * @throws IOException tcp-connection / socket-IO
     */
    public ModBusTcpConnection connect() throws IOException {
        this.socket = new Socket(this.target, this.port);
        this.inputStream = this.socket.getInputStream();
        this.outputStream = this.socket.getOutputStream();

        return this;
    }

    /**
     * Closes TCP-connection to server
     *
     * @return self
     * @throws IOException tcp-connection / socket-IO
     */
    public ModBusTcpConnection disconnect() throws IOException {
        if (socket != null) this.socket.close();
        if (inputStream != null) this.inputStream.close();
        if (outputStream != null) this.outputStream.close();
        return this;
    }

    /**
     * Transmits data within ModbusSession to server
     *
     * @param modBusSession managed session with current request packet
     * @return self
     * @throws IOException tcp-connection / socket-IO
     */
    public ModBusTcpConnection sendRequest(ModBusSession modBusSession) throws IOException {
        if (outputStream != null && modBusSession != null) {
            outputStream.write(modBusSession.getBytes());
            modBusSession.incrementSession();
            outputStream.flush();
        }
        return this;
    }

    /**
     * Awaits data from server
     *
     * @param bufferSize estimated by session (required buffer size to fit response)
     * @return self
     * @throws IOException tcp-connection / socket-IO
     */
    public byte[] receiveResponse(int bufferSize) throws IOException {
        byte[] buffer = new byte[bufferSize];

        if (this.inputStream != null) {
            int bytesRead = this.inputStream.read(buffer);
            byte[] response = new byte[bytesRead];
            System.arraycopy(buffer, 0, response, 0, bytesRead);
            return response;
        }
        return null;
    }
}