package net.opgenorth.shakespeare;


import android.app.Fragment;
import android.content.res.Resources;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ScrollView;
import android.widget.TextView;


public class DetailsFragment extends Fragment {
    private int _playId;

    public static DetailsFragment newInstance(int playId) {
        DetailsFragment fragment = new DetailsFragment();
        Bundle args = new Bundle();
        args.putInt(Shakespeare.PLAY_ID, playId);
        fragment.setArguments(args);
        return fragment;
    }

    public DetailsFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            _playId = getArguments().getInt(Shakespeare.PLAY_ID, 0);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        if (container == null) {
            // For some reason we're in a layout without a container - no reason to create the view.
            return null;
        }

        ScrollView scroller = new ScrollView(getActivity());
        scroller.addView(createTextViewFor(_playId));
        return scroller;

    }

    private TextView createTextViewFor(int playId) {
        Resources res = getActivity().getResources();

        TextView tv = new TextView(getActivity());
        tv.setText(Shakespeare.DIALOGUE[playId]);
        tv.setTextSize(TypedValue.COMPLEX_UNIT_SP, res.getDimension(R.dimen.details_frag_text_size));

        int padding = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, res.getDimension(R.dimen.details_frag_text_padding), res.getDisplayMetrics());
        tv.setPadding(padding, padding, padding, padding);

        return tv;
    }
}
