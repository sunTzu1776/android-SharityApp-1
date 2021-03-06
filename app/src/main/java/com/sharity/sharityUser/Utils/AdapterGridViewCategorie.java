package com.sharity.sharityUser.Utils;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v4.content.ContextCompat;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.matthewtamlin.android_utilities_library.views.SquareImageView;
import com.sharity.sharityUser.BO.Category;
import com.sharity.sharityUser.BO.Drawer;
import com.sharity.sharityUser.BO.LocationBusiness;
import com.sharity.sharityUser.R;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;

import static com.sharity.sharityUser.R.id.imageView;
import static com.sharity.sharityUser.fragment.client.client_Container_Partenaire_fragment.mSelectedItem_categories;


public class AdapterGridViewCategorie extends BaseAdapter{

    OnItemGridCategorieClickListener categorieClickListener;
    Context context;
    LayoutInflater inflat;
    ArrayList<Category> item=new ArrayList<>();
    private static LayoutInflater inflater=null;
    public interface OnItemGridCategorieClickListener {
        void onItemCategorieClick(int item, String categorie,View view);
    }

    public AdapterGridViewCategorie(Context context, ArrayList<Category> item, OnItemGridCategorieClickListener categorieClickListener) {
        // TODO Auto-generated constructor stub
        inflat = LayoutInflater.from(context);
        this.categorieClickListener=categorieClickListener;
        this.item=item;
        this.context=context;
        inflater = ( LayoutInflater )context.
                getSystemService(Context.LAYOUT_INFLATER_SERVICE);
 
    }
 
    @Override
    public int getCount() {
        // TODO Auto-generated method stub
        return item.size();
    }
 
    @Override
    public Object getItem(int position) {
        // TODO Auto-generated method stub
        return position;
    }
 
    @Override
    public long getItemId(int position) {
        // TODO Auto-generated method stub
        return position;
    }
 


    private class ViewHolder {
        TextView os_text;
        RelativeLayout background;
        ImageView os_img;
        FrameLayout overlay;
        public String toString() {
            return "-";
        }
    }


    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        AdapterGridViewCategorie.ViewHolder holder = null;
        final Category it = item.get(position);

        if (convertView == null) {
            holder = new AdapterGridViewCategorie.ViewHolder();
            convertView = inflat.inflate(R.layout.sample_gridlayout, null);

            holder.os_text =(TextView) convertView.findViewById(R.id.os_texts);
            holder.os_img =(ImageView) convertView.findViewById(R.id.icon);
            holder.background =(RelativeLayout) convertView.findViewById(R.id.background);
            holder.overlay =(FrameLayout) convertView.findViewById(R.id.overlay);

            holder.os_img.getLayoutParams().height = (int) (Utils.getScreenWidth(context) / 3);
            holder.os_img.requestLayout();

            convertView.setTag(holder);
        }
         else {
            holder = (AdapterGridViewCategorie.ViewHolder) convertView.getTag();
        }

        if (it.getNom()!=null){
            holder.os_text.setText(it.getNom());
        }

        if(mSelectedItem_categories == position){
                holder.overlay.setVisibility(View.INVISIBLE);

        }else{
            holder.overlay.setVisibility(View.VISIBLE);
        }

        holder.os_img.setTag(position);
        if(it.get_image()!=null){
            Bitmap PictureProfile = BitmapFactory.decodeByteArray(it.get_image(), 0, it.get_image().length);
            holder.os_img.setImageBitmap(PictureProfile);
        }

        final View finalConvertView = convertView;
        holder.os_img.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                mSelectedItem_categories = position;
                notifyDataSetChanged();
                categorieClickListener.onItemCategorieClick(position, it.getObjectid(), finalConvertView);
            }
        });
        return convertView;
    }

}