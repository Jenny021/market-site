package com.example.market_app;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import com.example.market_app.databinding.FragmentArticleDetailBinding;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ArticleDetailFragment extends Fragment {

    private FragmentArticleDetailBinding binding;
    private ApiService apiService;
    private String articleUuid;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentArticleDetailBinding.inflate(inflater, container, false);
        apiService = RetrofitClient.getApiService();
        View view = binding.getRoot();

        if (getArguments() != null) {
            articleUuid = getArguments().getString("article_uuid");
            loadArticleDetails(articleUuid);
        }

        binding.saveButton.setOnClickListener(v -> saveArticle());
        binding.deleteButton.setOnClickListener(v -> deleteArticle());

        return view;
    }

    private void loadArticleDetails(String uuid) {
        apiService.getArticle(uuid).enqueue(new Callback<Article>() {
            @Override
            public void onResponse(@NonNull Call<Article> call, @NonNull Response<Article> response) {
                if (response.isSuccessful()) {
                    Article article = response.body();
                    if (article != null) {
                        binding.articleNameEdit.setText(article.getName());
                        binding.articleCostEdit.setText(String.valueOf(article.getCost()));
                    }
                } else {
                    Toast.makeText(getContext(), "Failed", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(@NonNull Call<Article> call, @NonNull Throwable t) {
                Toast.makeText(getContext(), "Failed", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void saveArticle() {
        String name = binding.articleNameEdit.getText().toString();
        double cost = Double.parseDouble(binding.articleCostEdit.getText().toString());

        Article article = new Article(articleUuid, name, cost);
        apiService.updateArticle(articleUuid, article).enqueue(new Callback<Article>() {
            @Override
            public void onResponse(@NonNull Call<Article> call, @NonNull Response<Article> response) {
                if (response.isSuccessful()) {
                    Toast.makeText(getContext(), "Success" , Toast.LENGTH_SHORT).show();
                    Navigation.findNavController(requireView()).popBackStack();
                } else {
                    Toast.makeText(getContext(), "Failed" , Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(@NonNull Call<Article> call, @NonNull Throwable t) {
                Toast.makeText(getContext(), "Failed" , Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void deleteArticle() {
        apiService.deleteArticle(articleUuid).enqueue(new Callback<Void>() {
            @Override
            public void onResponse(@NonNull Call<Void> call, @NonNull Response<Void> response) {
                if (response.isSuccessful()) {
                    Toast.makeText(getContext(), "Success", Toast.LENGTH_SHORT).show();
                    Navigation.findNavController(requireView()).popBackStack();
                } else {
                    Toast.makeText(getContext(), "Delete failed", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(@NonNull Call<Void> call, @NonNull Throwable t) {
                Toast.makeText(getContext(), "Failed" , Toast.LENGTH_SHORT).show();
            }
        });
    }
}
