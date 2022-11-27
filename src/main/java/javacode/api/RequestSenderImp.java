package javacode.api;

import com.google.gson.JsonElement;
import com.vk.api.sdk.client.TransportClient;
import com.vk.api.sdk.client.VkApiClient;
import com.vk.api.sdk.client.actors.ServiceActor;
import com.vk.api.sdk.exceptions.ApiException;
import com.vk.api.sdk.exceptions.ClientException;
import com.vk.api.sdk.httpclient.HttpTransportClient;
import com.vk.api.sdk.objects.users.Fields;
import com.vk.api.sdk.objects.users.UserXtrCounters;
import com.vk.api.sdk.objects.users.responses.GetResponse;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class RequestSenderImp implements RequestSender {
    //String request = String.format("%s?user_id=%s&album_id=profile&access_token=%s&v=%s",url,vkId,accessToken,version);

    private static final String accessToken = "///";
    private static final Integer appId = 1111111;


    @Override
    public String sendImgRequest(String vkId) {
        try {
            TransportClient transportClient = new HttpTransportClient();
            VkApiClient vk = new VkApiClient(transportClient);
            List<GetResponse> response = vk.users().get(new ServiceActor(appId, accessToken))
                    .userIds(vkId)
                    .fields(Fields.PHOTO_200_ORIG)
                    .execute();
            System.out.println(response);
            GetResponse json = response.get(0);
            String imgUrl = String.valueOf(json.getPhoto200Orig());
            return imgUrl;
        } catch (ClientException | ApiException e) {
            throw new RuntimeException(e);
        }
    }


}
