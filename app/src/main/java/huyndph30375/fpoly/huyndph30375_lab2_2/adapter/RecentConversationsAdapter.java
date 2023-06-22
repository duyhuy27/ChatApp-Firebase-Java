package huyndph30375.fpoly.huyndph30375_lab2_2.adapter;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Base64;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import huyndph30375.fpoly.huyndph30375_lab2_2.databinding.ItemsContainerRecentConversionBinding;
import huyndph30375.fpoly.huyndph30375_lab2_2.listener.ConversionListenr;
import huyndph30375.fpoly.huyndph30375_lab2_2.models.ChatMessage;
import huyndph30375.fpoly.huyndph30375_lab2_2.models.User;

public class RecentConversationsAdapter extends  RecyclerView.Adapter<RecentConversationsAdapter.ConversionViewHolder>{

    private final List<ChatMessage> chatMessageLists;
    private final ConversionListenr conversionListenr;

    public RecentConversationsAdapter(List<ChatMessage> chatMessageLists,ConversionListenr conversionListenr) {
        this.chatMessageLists = chatMessageLists;
        this.conversionListenr = conversionListenr;
    }

    @NonNull
    @Override
    public ConversionViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ConversionViewHolder(
                ItemsContainerRecentConversionBinding.inflate(LayoutInflater.from(parent.getContext()),
                        parent,
                        false)
        );
    }

    @Override
    public void onBindViewHolder(@NonNull ConversionViewHolder holder, int position) {

        holder.setData(chatMessageLists.get(position));

    }

    @Override
    public int getItemCount() {
        return chatMessageLists.size();
    }

    class ConversionViewHolder extends RecyclerView.ViewHolder {
        ItemsContainerRecentConversionBinding binding;

        ConversionViewHolder(ItemsContainerRecentConversionBinding itemsContainerRecentConversionBinding) {
            super(itemsContainerRecentConversionBinding.getRoot());
            binding = itemsContainerRecentConversionBinding;
        }

        void setData(ChatMessage chatMessage) {
            binding.imageProfile.setImageBitmap(getConversionImage((chatMessage.conversionImage)));
            binding.textName.setText(chatMessage.conversionName);
            binding.textRecentMessage.setText(chatMessage.message);
            binding.getRoot().setOnClickListener(v -> {
                User user = new User();
                user.id = chatMessage.conversionId;
                user.name = chatMessage.conversionName;
                user.image = chatMessage.conversionImage;
                conversionListenr.onConversionClicked(user);
            });
        }
    }

    private Bitmap getConversionImage(String encodedImage){
        byte[] bytes = Base64.decode(encodedImage, Base64.DEFAULT);
        return BitmapFactory.decodeByteArray(bytes, 0, bytes.length);
    }
}
