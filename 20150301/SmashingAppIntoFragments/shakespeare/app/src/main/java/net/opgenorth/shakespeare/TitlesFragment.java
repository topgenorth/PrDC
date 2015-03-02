package net.opgenorth.shakespeare;

import android.app.FragmentTransaction;
import android.app.ListFragment;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;


public class TitlesFragment extends ListFragment {
    private boolean _dualPane;
    private int _checkCurrentPosition = 0;

    public static TitlesFragment newInstance() {
        TitlesFragment fragment = new TitlesFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    public TitlesFragment() {
        // Required empty public constructor
    }


    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        View detailsFrame = getActivity().findViewById(R.id.details);
        _dualPane = detailsFrame != null && detailsFrame.getVisibility() == View.VISIBLE;

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_checked, Shakespeare.TITLES);
        setListAdapter(adapter);

        if (savedInstanceState != null) {
            _checkCurrentPosition = savedInstanceState.getInt(Shakespeare.PLAY_ID, 0);
        }

        if (_dualPane) {
            getListView().setChoiceMode(ListView.CHOICE_MODE_SINGLE);
            showDetails(_checkCurrentPosition);
        }
    }

    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {
        showDetails(position);
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt(Shakespeare.PLAY_ID, _checkCurrentPosition);
    }

    private void showDetails(int playId) {
        _checkCurrentPosition = playId;

        if (_dualPane) {
            getListView().setItemChecked(playId, true);
            DetailsFragment frag = DetailsFragment.newInstance(playId);

            FragmentTransaction ft = getFragmentManager().beginTransaction();
            ft.replace(R.id.details, frag, Shakespeare.DETAILS_FRAG);
            ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
            ft.commit();
        } else {
            Intent i = new Intent();
            i.setClass(getActivity(), DetailsActivity.class);
            i.putExtra(Shakespeare.PLAY_ID, playId);
            startActivity(i);

        }
    }
}