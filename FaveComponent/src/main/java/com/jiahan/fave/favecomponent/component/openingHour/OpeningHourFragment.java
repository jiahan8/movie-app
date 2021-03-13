package com.jiahan.fave.favecomponent.component.openingHour;

import android.os.Bundle;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;

import com.jiahan.fave.favecomponent.R;
import com.jiahan.fave.favecomponent.databinding.FragmentOutletOpeningHourBinding;
import com.jiahan.fave.favecomponent.entity.OutletBusinessHour;
import com.jiahan.fave.favecomponent.view.BlurDialogFragment;

import java.util.ArrayList;
import java.util.List;

public class OpeningHourFragment extends BlurDialogFragment {
    public static final  String TAG                        = OpeningHourFragment.class.getName() + ".";
    private static final String EXTRA_OUTLET_OPENING_HOURS = TAG + "EXTRA_OUTLET_OPENING_HOURS";

    private List<OutletBusinessHour> mOutletBusinessHourList;

    public static OpeningHourFragment newInstance(final ArrayList<OutletBusinessHour> outletBusinessHourList) {
        final Bundle bundle = new Bundle();
        bundle.putParcelableArrayList(EXTRA_OUTLET_OPENING_HOURS, outletBusinessHourList);
        final OpeningHourFragment fragment = new OpeningHourFragment();
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void onViewCreated(@NonNull final View view, final Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        getInputData();
        bindViewModel();
    }

    @Override
    protected int getLayoutResource() {
        return R.layout.fragment_outlet_opening_hour;
    }

    private void getInputData() {
        Bundle bundle = getArguments();
        mOutletBusinessHourList = bundle.getParcelableArrayList(EXTRA_OUTLET_OPENING_HOURS);
    }

    private void bindViewModel() {
        OpeningHourViewModel viewModel = new OpeningHourViewModelImpl(this, mOutletBusinessHourList);
        FragmentOutletOpeningHourBinding binding = DataBindingUtil.bind(mContentView);
        binding.setViewModel(viewModel);
    }
}