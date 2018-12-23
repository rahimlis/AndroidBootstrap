package com.bakudynamics.android.bootstrap.views;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bakudynamics.android.bootstrap.App;


public class BaseFragment extends Fragment {


    protected static final String ARG_LAYOUT_RES_ID = "layoutResId";
    protected OnFragmentInteractionListener onFragmentInteractionListener;
    protected int layoutResId;
    protected Context context;
    protected View rootView;

    public BaseFragment() {
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null && getArguments().containsKey(ARG_LAYOUT_RES_ID)) {
            layoutResId = getArguments().getInt(ARG_LAYOUT_RES_ID);
        }
    }


    public View getRootView() {
        return rootView;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(layoutResId, container, false);
    }

    @Override
    public void onAttach(Context activity) {
        super.onAttach(activity);
        this.context = activity;
        if (context instanceof OnFragmentInteractionListener) {
            onFragmentInteractionListener = (OnFragmentInteractionListener) context;
        }
    }


    @Override
    public void onDetach() {
        super.onDetach();
        onFragmentInteractionListener = null;
    }

    public BaseFragment withView(int resId) {
        Bundle args = new Bundle();
        args.putInt(ARG_LAYOUT_RES_ID, resId);
        setArguments(args);
        return this;
    }

    @Nullable
    protected App getApplication() {
        if (getActivity() == null)
            return null;

        return ((App) getActivity().getApplication());
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {

    }

}
