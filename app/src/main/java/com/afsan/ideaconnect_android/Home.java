package com.afsan.ideaconnect_android;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.preference.PreferenceManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.afsan.ideaconnect_android.Adapter.IdeaAdapter;
import com.afsan.ideaconnect_android.Model.API;
import com.afsan.ideaconnect_android.Model.ApiInterface;
import com.afsan.ideaconnect_android.Model.IdeaModel;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link Home#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Home extends Fragment {

    String url = "https://backend.btocode.repl.co/api/token/";
    RecyclerView idearv;
    Button btn_logout;

//    ArrayList<IdeaModel> ideaList;


    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public Home() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment Home.
     */
    // TODO: Rename and change types and number of parameters
    public static Home newInstance(String param1, String param2) {
        Home fragment = new Home();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
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
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        btn_logout = view.findViewById(R.id.logout);
        SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(getActivity());
        SharedPreferences.Editor editor = sp.edit();
        btn_logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(),LoginActivity.class);
                startActivity(intent);
                System.out.println("Clicked on logout before");
                editor.clear();
                editor.commit();
                System.out.println("Clicked on logout after");
            }
        });


        idearv = view.findViewById(R.id.viewIdeas);
        final ArrayList<IdeaModel>ideaList = new ArrayList<>();
        int x = 0;

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(url)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        ApiInterface api = retrofit.create(ApiInterface.class);
        Call<List<API>> call = api.getModels();
        call.enqueue(new Callback<List<API>>() {
            @Override
            public void onResponse(Call<List<API>> call, Response<List<API>> response) {
                List<API> data = response.body();
                ArrayList<IdeaModel>ideaList = new ArrayList<>();

                for(int i = 0; i< data.size();i++){
                    API info = data.get(i);
//                    System.out.println("Printing-> "+info.getFirst_name());
                    String tag[] = info.getIdeaTags().split(" ");
//                    System.out.println(tag[0] + " tags are here "+ tag[1]);
                    String tags = "";
                    for (int j = 0; j< tag.length;j++){
                        tags = tags + " " + "#"+tag[j];
                    }
//                    System.out.println(info.getSuggestions().length);
//                    ideaList.add(new IdeaModel(R.drawable.ic_profile,"Afsan Saeed","g software like Aldus ","Title of the Idea","#AI #ML #RNN","22","522","22"));
//                    viewData(data.get(i).getUserInfo().getFirstName(),data.get(i).getUserInfo().getLastName(),data.get(i).getIdeatitle(),data.get(i).getIdeaDesc())
                    ideaList.add(new IdeaModel(R.drawable.ic_profile,info.getIdeaId(),info.getVoteCounter(),info.getAuthor(),info.getIdeaTitle(),info.getIdeaDesc(),tags,info.getLast_name(),info.getFirst_name(),info.getUpvotes(),info.getDownvotes(),info.getSuggestions()));
                }
                IdeaAdapter ideaAdapter = new IdeaAdapter(ideaList,getContext());
                LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
                idearv.setLayoutManager(layoutManager);
                idearv.setNestedScrollingEnabled(false);
                idearv.setAdapter(ideaAdapter);
            }
            @Override
            public void onFailure(Call<List<API>> call, Throwable t) {

                Log.e("home test",t.getMessage());
            }
        });

//            ideaList.add(new IdeaModel(R.drawable.ic_profile,"Afsan Saeed","g software like Aldus ","Title of the Idea","#AI #ML #RNN","22","522","22"));
        return view;
    }
}