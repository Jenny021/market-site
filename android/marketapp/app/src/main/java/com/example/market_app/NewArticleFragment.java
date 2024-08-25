package com.example.market_app;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.databinding.DataBindingUtil;

import com.example.market_app.databinding.FragmentNewArticleBinding;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class NewArticleFragment extends Fragment {
    private SharedViewModel sharedViewModel;
    private FragmentNewArticleBinding binding;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_new_article, container, false);
        sharedViewModel = new ViewModelProvider(requireActivity()).get(SharedViewModel.class);

        binding.buttonSave.setOnClickListener(v -> saveArticle());
        binding.buttonCancel.setOnClickListener(v -> Navigation.findNavController(requireView()).navigateUp());

        return binding.getRoot();
    }

    private void saveArticle() {
        if (isValidInput()) {
            String name = binding.editArticleName.getText().toString().trim();
            double cost = Double.parseDouble(binding.editArticleCost.getText().toString().trim());

            Article newArticle = new Article("", name, cost);

            ApiService apiService = RetrofitClient.getApiService();
            Call<Article> call = apiService.createArticle(newArticle);
            call.enqueue(new Callback<Article>() {
                @Override
                public void onResponse(Call<Article> call, Response<Article> response) {
                    if (response.isSuccessful()) {
                        System.out.println(response.body());
                        sharedViewModel.notifyArticleCreated();
                        Toast.makeText(getContext(), "Article created successfully", Toast.LENGTH_SHORT).show();
                        NavController navController = Navigation.findNavController(requireActivity(), R.id.nav_host_fragment);
                        navController.navigate(R.id.action_newArticleFragment_to_articleListFragment);
                    } else {
                        Toast.makeText(getContext(), "Creation failed: " + response.message(), Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onFailure(Call<Article> call, Throwable t) {
                    Toast.makeText(getContext(), "Creation failed: " + t.getMessage(), Toast.LENGTH_SHORT).show();
                }
            });
        } else {
            Toast.makeText(requireContext(), "Please fill in all fields correctly", Toast.LENGTH_SHORT).show();
        }
    }


    private boolean isValidInput() {
        String name = binding.editArticleName.getText().toString().trim();
        String costStr = binding.editArticleCost.getText().toString().trim();

        if (TextUtils.isEmpty(name) || TextUtils.isEmpty(costStr)) {
            return false;
        }

        try {
            Double.parseDouble(costStr);
        } catch (NumberFormatException e) {
            return false;
        }

        return true;
    }
}
