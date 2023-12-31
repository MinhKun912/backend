package ra.chat;


import lombok.*;


@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class ChatMessage {
    private MessageType type;
    private String content;
    private String sender;
}
