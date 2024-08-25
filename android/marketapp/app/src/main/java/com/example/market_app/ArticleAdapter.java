package com.example.market_app;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

import com.example.market_app.databinding.ItemArticleBinding;

import java.util.List;
import java.util.Objects;

public class ArticleAdapter extends RecyclerView.Adapter<ArticleAdapter.ArticleViewHolder> {
    List<Article> articleList;
    private OnItemClickListener onItemClickListener;

    public ArticleAdapter(List<Article> articles, OnItemClickListener onItemClickListener) {
        super();
        this.articleList = articles;
        this.onItemClickListener = onItemClickListener;
    }

    @NonNull
    @Override
    public ArticleViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        ItemArticleBinding binding = ItemArticleBinding.inflate(inflater, parent, false);
        return new ArticleViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull ArticleViewHolder holder, int position) {
        holder.bindTo(articleList.get(position));
    }

    @Override
    public int getItemCount() {
        return this.articleList.size();
    }

    public interface OnItemClickListener {
        void onItemClick(Article article);
    }

    class ArticleViewHolder extends RecyclerView.ViewHolder {
        private final ItemArticleBinding binding;

        public ArticleViewHolder(ItemArticleBinding binding) {
            super(binding.getRoot());
            this.binding = binding;

            binding.getRoot().setOnClickListener(v -> {
                Article article = binding.getArticle();
                if (article != null) {
                    onItemClickListener.onItemClick(article);
                }
            });
        }

        public void bindTo(Article article) {
            System.out.println(article);
            binding.setArticle(article);
            binding.executePendingBindings();
        }
    }
    private static final DiffUtil.ItemCallback<Article> DIFF_CALLBACK = new DiffUtil.ItemCallback<Article>() {
        @Override
        public boolean areItemsTheSame(@NonNull Article oldItem, @NonNull Article newItem) {
            return oldItem.getUuid().equals(newItem.getUuid());
        }

        @Override
        public boolean areContentsTheSame(@NonNull Article oldItem, @NonNull Article newItem) {
            return oldItem.getUuid().equals(newItem.getUuid());
        }
    };
}
