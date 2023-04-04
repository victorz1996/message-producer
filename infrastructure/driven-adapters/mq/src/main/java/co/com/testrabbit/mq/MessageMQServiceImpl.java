package co.com.testrabbit.mq;

import co.com.testrabbit.model.message.Message;
import co.com.testrabbit.model.message.gateways.MessageMQService;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

@RequiredArgsConstructor
@Component
public class MessageMQServiceImpl implements MessageMQService {

    private final RabbitTemplate rabbitTemplate;
    @Value("${broker.exchange}")
    private String exchange;

    @Override
    public Mono<Boolean> send(Message message, String routingKey) {
        try {
            rabbitTemplate.convertAndSend(exchange, routingKey, message);
            return Mono.just(Boolean.TRUE);
        } catch (Exception e) {
            return Mono.just(Boolean.FALSE);
        }
    }
}
