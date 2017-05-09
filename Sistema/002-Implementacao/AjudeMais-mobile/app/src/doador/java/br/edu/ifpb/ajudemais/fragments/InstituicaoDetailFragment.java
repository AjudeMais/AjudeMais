package br.edu.ifpb.ajudemais.fragments;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.CardView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import br.edu.ifpb.ajudemais.R;
import br.edu.ifpb.ajudemais.adapters.CategoriasAdapter;
import br.edu.ifpb.ajudemais.adapters.InstituicoesAdapter;
import br.edu.ifpb.ajudemais.domain.InstituicaoCaridade;
import br.edu.ifpb.ajudemais.listeners.RecyclerItemClickListener;

/**
 * <p>
 * <b>InstituicaoDetailFragment</b>
 * </p>
 * <p>
 * InstituicaoDetailFragment para lista de instituições
 * <p>
 * <p>
 * </p>
 *
 * @author <a href="https://github.com/FranckAJ">Franck Aragão</a>
 */
public class InstituicaoDetailFragment extends Fragment {

    private RecyclerView recyclerView;
    private TextView descricaoInstituicao;
    private TextView emailInstituicao;
    private TextView logradouroInstituicao;
    private TextView localidadeInstituicao;
    private CardView cardViewEmail;
    private InstituicaoCaridade instituicaoCaridade;
    private CategoriasAdapter categoriasAdapter;
    private TextView listInstituicoes;

    /**
     * @param inflater
     * @param container
     * @param savedInstanceState
     * @return
     */
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.fragment_instituicao_detail, container, false);
        setHasOptionsMenu(true);
        recyclerView = (RecyclerView) v.findViewById(R.id.recycle_view_list);


        return v;
    }

    /**
     * @param view
     * @param savedInstanceState
     */
    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        Intent intentInstituicao = getActivity().getIntent();
        instituicaoCaridade = (InstituicaoCaridade) intentInstituicao.getSerializableExtra("instituicao");

        descricaoInstituicao = (TextView) getView().findViewById(R.id.tv_instituicao_detail_descricao);
        emailInstituicao = (TextView) getView().findViewById(R.id.tv_instituicao_detail_email);
        logradouroInstituicao = (TextView) getView().findViewById(R.id.tv_instituicao_detail_logradouro);
        localidadeInstituicao = (TextView) getView().findViewById(R.id.tv_instituicao_detail_localidade);

        listInstituicoes = (TextView) getView().findViewById(R.id.tv_list_itens_doaveis);

        if(instituicaoCaridade.getItensDoaveis().size()<1){
            listInstituicoes.setVisibility(View.GONE);
        }

        cardViewEmail = (CardView) getView().findViewById(R.id.card_view_intituicao_detail_email);


        RecyclerView.LayoutManager layout = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(layout);
        categoriasAdapter = new CategoriasAdapter(instituicaoCaridade.getItensDoaveis(), getActivity());
        recyclerView.setAdapter(categoriasAdapter);

//        recyclerView.addOnItemTouchListener(new RecyclerItemClickListener(getActivity(), clickListener));

    }

    /**
     *
     */
    @Override
    public void onStart() {
        super.onStart();


        descricaoInstituicao.setText(instituicaoCaridade.getDescricao());
        emailInstituicao.setText(instituicaoCaridade.getConta().getEmail());
        logradouroInstituicao.setText(instituicaoCaridade.getEndereco().getLogradouro() + ", " +
                instituicaoCaridade.getEndereco().getNumero());
        localidadeInstituicao.setText(
                instituicaoCaridade.getEndereco().getBairro() + ", " +
                        instituicaoCaridade.getEndereco().getLocalidade() + " - " +
                        instituicaoCaridade.getEndereco().getUf());

        cardViewEmail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sendEmail(instituicaoCaridade.getConta().getEmail());
            }
        });



    }

    /**
     * Criar intent apropriada para abrir o app do gmail.
     *
     * @param email
     */
    private void sendEmail(String email) {
        Intent emailIntent = new Intent(Intent.ACTION_SEND);
        emailIntent.setType("text/html");
        final PackageManager pm = getActivity().getPackageManager();
        final List<ResolveInfo> matches = pm.queryIntentActivities(emailIntent, 0);
        String className = null;
        for (final ResolveInfo info : matches) {
            if (info.activityInfo.packageName.equals("com.google.android.gm")) {
                className = info.activityInfo.name;

                if (className != null && !className.isEmpty()) {
                    break;
                }
            }
        }
        emailIntent.setClassName("com.google.android.gm", className);
        emailIntent.putExtra(Intent.EXTRA_EMAIL, new String[]{email});
        emailIntent.putExtra(Intent.EXTRA_SUBJECT, "[Ajude Mais App]");
        startActivity(emailIntent);
    }
}