import java.io.Serializable;

public class MessagePacket implements Serializable {
    private String name;
    private String message;

    public MessagePacket(String name, String message) {
        this.name = name;
        this.message = message;
    } // fim do construtor MessagePacket(String, String)

    public String getName() {
        return name;
    } // fim do método getName()

    public String getMessage() {
        return message;
    } // fim do método getMessage()

    @Override
    public String toString() {
        String format = "MessagePacket { name = \"%s\", message = \"%s\" }";
        return String.format(format, this.name, this.message);
    } // fim do método toString()

} // fim da classe MessagePacket
