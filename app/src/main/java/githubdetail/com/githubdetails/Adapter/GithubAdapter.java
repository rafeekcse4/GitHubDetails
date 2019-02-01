package githubdetail.com.githubdetails.Adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

import githubdetail.com.githubdetails.Model.Item;
import githubdetail.com.githubdetails.R;


public class GithubAdapter extends RecyclerView.Adapter<GithubAdapter.MyViewHolder> {

    private Context context;
    private List<Item> list;

    private ClickLister clickLister;

    public GithubAdapter (Context context, List<Item> list){

        this.context = context;
        this.list = list;
    }


    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_launch_list, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder myViewHolder, int position) {
        Item item=list.get(position);
        String imageLink= item.getOwner().getAvatarUrl();
        //   myViewHolder.img_avatar.setImageUrl(Url,imageLoader);

        if(imageLink!=null && !"".equals(item.getOwner().getAvatarUrl())){
            Picasso.with(context)
                    .load(imageLink)
                    .placeholder(R.drawable.img_msk_profile)
                    .error(R.drawable.ic_launcher_background)
                    // To fit image into imageView
                    .fit()
                    // To prevent fade animation
                    .noFade()
                    .into(myViewHolder.img_avatar);
        }else{
            myViewHolder.img_avatar.setImageDrawable(ContextCompat.getDrawable(context,R.drawable.img_msk_profile));
        }

        if (null != item.getFullName() && !"".equals(item.getFullName())){
         myViewHolder.txt_project.setText(item.getFullName());
        }
        String languagename=item.getLanguage();

        if (null !=  languagename && !"".equals(languagename)){
            myViewHolder.txt_languagename.setText(item.getLanguage());
        }

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public void setClickLister(ClickLister  clickLister ) {
        this.clickLister=clickLister;
    }

    public interface ClickLister {
        void itemClick(View view, int position, Item item);
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{

        TextView txt_project;
        TextView txt_languagename;
        ImageView img_avatar;

        MyViewHolder(@NonNull View itemView) {
            super(itemView);
            txt_languagename=itemView.findViewById(R.id.id_txt_language);
            txt_project=itemView.findViewById(R.id.id_txt_projectname);
            img_avatar=itemView.findViewById(R.id.id_imgavatar);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    clickLister.itemClick(v,getAdapterPosition(),list.get(getAdapterPosition()));
                }
            });
        }
    }
}
