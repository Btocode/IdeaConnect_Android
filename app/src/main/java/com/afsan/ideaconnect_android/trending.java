package com.afsan.ideaconnect_android;

import android.os.Bundle;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.afsan.ideaconnect_android.Adapter.IdeaAdapter;
import com.afsan.ideaconnect_android.Adapter.TrendingPostAdapter;
import com.afsan.ideaconnect_android.Model.API;
import com.afsan.ideaconnect_android.Model.ApiInterface;
import com.afsan.ideaconnect_android.Model.IdeaModel;
import com.afsan.ideaconnect_android.Model.TrendingPostModel;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link trending#newInstance} factory method to
 * create an instance of this fragment.
 *
 */
public class trending extends Fragment {


    RecyclerView idearv;
    ArrayList<TrendingPostModel> ideaList;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment trending.
     */
    // TODO: Rename and change types and number of parameters
    public static trending newInstance(String param1, String param2) {
        trending fragment = new trending();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    public trending() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_trending, container, false);

        idearv = view.findViewById(R.id.trendingContainer);
        ideaList = new ArrayList<>();


//        ideaList.add(new TrendingPostModel(R.drawable.ic_profile,"Hasnayeen Ornil","Title Of the Post","#AI #ML #RNN","52","45","32"));


        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://backend.btocode.repl.co/api/token/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        ApiInterface api = retrofit.create(ApiInterface.class);
        Call<List<API>> call = api.getModels();
        call.enqueue(new Callback<List<API>>() {
            @Override
            public void onResponse(Call<List<API>> call, Response<List<API>> response) {
                List<API> data = response.body();
                ArrayList<TrendingPostModel>ideaList = new ArrayList<>();
                System.out.println("From trending post -> " +response);
                for(int i = 0; i< data.size();i++){
                    API info = data.get(i);
//                    System.out.println("Printing-> "+info.getFirst_name());
                    String tag[] = info.getIdeaTags().split(" ");
//                    System.out.println(tag[0] + " tags are here "+ tag[1]);
                    String tags = "";
                    for (int j = 0; j< tag.length;j++){
                        tags = tags + " " + "#"+tag[j];
                    }
//                      ideaList.add(new TrendingPostModel(R.drawable.ic_profile,"Hasnayeen Ornil","Title Of the Post","#AI #ML #RNN","52","45","32"));
//                      ideaList.add(new TrendingPostModel(R.drawable.ic_profile,info.getFirst_name() + info.getLast_name(),info.getIdeaTitle(),tags,String.valueOf(info.getSuggestions().length),String.valueOf(info.getUpvotes().length),String.valueOf(info.getDownvotes().length)));
                      ideaList.add(new TrendingPostModel(R.drawable.ic_profile,info.getFirst_name() + info.getLast_name(),info.getIdeaTitle(),tags,"35",String.valueOf(info.getUpvotes().length),String.valueOf(info.getDownvotes().length)));

                }
                TrendingPostAdapter trendingPostAdapter = new TrendingPostAdapter(ideaList,getContext());
                LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
                idearv.setLayoutManager(layoutManager);
                idearv.setNestedScrollingEnabled(false);
                idearv.setAdapter(trendingPostAdapter);
            }
            @Override
            public void onFailure(Call<List<API>> call, Throwable t) {

                Log.e("home test",t.getMessage());
            }
        });

        return view;
    }
}