package com.example.market_app;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.market_app.databinding.FragmentArticleListBinding;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import java.util.List;

public class ArticleListFragment extends Fragment {
    private SharedViewModel sharedViewModel;
    private FragmentArticleListBinding binding;
    private ApiService apiService;
    private ArticleAdapter adapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentArticleListBinding.inflate(inflater, container, false);
        apiService = RetrofitClient.getApiService();


        sharedViewModel = new ViewModelProvider(requireActivity()).get(SharedViewModel.class);

        sharedViewModel.getArticleCreated().observe(getViewLifecycleOwner(), articleCreated -> {
            if (articleCreated) {
                loadArticles();
                sharedViewModel.resetArticleCreated();
            }
        });

        binding.fab.setOnClickListener(v ->
                Navigation.findNavController(requireView())
                        .navigate(R.id.action_articleListFragment_to_newArticleFragment)
        );

        loadArticles();

        return binding.getRoot();
    }

    private void setupRecyclerView(List<Article> articles) {
        adapter = new ArticleAdapter(articles, new ArticleAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(Article article) {
                Bundle bundle = new Bundle();
                bundle.putString("article_uuid", article.getUuid());
                Navigation.findNavController(requireView())
                        .navigate(R.id.action_articleListFragment_to_articleDetailFragment, bundle);
            }
        });
        binding.recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        binding.recyclerView.setAdapter(adapter);
    }

    private void loadArticles() {
        apiService.getArticleList().enqueue(new Callback<List<Article>>() {
            @Override
            public void onResponse(@NonNull Call<List<Article>> call, @NonNull Response<List<Article>> response) {
                if (response.isSuccessful()) {
                    List<Article> articles = response.body();
                    setupRecyclerView(articles);
                } else {
                    Toast.makeText(getContext(), "Fetch failed", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(@NonNull Call<List<Article>> call, @NonNull Throwable t) {
                Toast.makeText(getContext(), "Load failed: " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}
