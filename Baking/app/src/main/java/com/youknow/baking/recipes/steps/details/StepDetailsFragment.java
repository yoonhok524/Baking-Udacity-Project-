package com.youknow.baking.recipes.steps.details;


import com.google.android.exoplayer2.C;
import com.google.android.exoplayer2.ExoPlayerFactory;
import com.google.android.exoplayer2.SimpleExoPlayer;
import com.google.android.exoplayer2.extractor.DefaultExtractorsFactory;
import com.google.android.exoplayer2.source.ExtractorMediaSource;
import com.google.android.exoplayer2.source.MediaSource;
import com.google.android.exoplayer2.trackselection.AdaptiveTrackSelection;
import com.google.android.exoplayer2.trackselection.DefaultTrackSelector;
import com.google.android.exoplayer2.trackselection.TrackSelection;
import com.google.android.exoplayer2.ui.SimpleExoPlayerView;
import com.google.android.exoplayer2.upstream.BandwidthMeter;
import com.google.android.exoplayer2.upstream.DataSource;
import com.google.android.exoplayer2.upstream.DefaultBandwidthMeter;
import com.google.android.exoplayer2.upstream.DefaultDataSourceFactory;
import com.google.android.exoplayer2.util.Util;

import com.bumptech.glide.Glide;
import com.youknow.baking.R;
import com.youknow.baking.data.Step;
import com.youknow.baking.recipes.StepListener;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * A simple {@link Fragment} subclass.
 */
public class StepDetailsFragment extends Fragment {

    private static final String TAG = StepDetailsFragment.class.getSimpleName();

    @BindView(R.id.tv_short_desc) TextView mTvShortDesc;
    @BindView(R.id.exoplayer_video) SimpleExoPlayerView mPlayerView;
    @BindView(R.id.iv_thumbnail) ImageView mIvThumbnail;
    @BindView(R.id.tv_desc) TextView mTvDesc;
    @BindView(R.id.btn_prev) Button mBtnPrev;
    @BindView(R.id.btn_next) Button mBtnNext;

    private StepListener mListener;
    private Step mStep;
    private int mCurrentStep;
    private int mStepSize;
    private MediaSource videoSource;
    private SimpleExoPlayer mPlayer;
    private DefaultTrackSelector mTrackSelector;
    private int resumeWindow;
    private long resumePosition;

    public StepDetailsFragment() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_step_details, container, false);
        ButterKnife.bind(this, root);

        getActivity().setTitle(getString(R.string.steps));

        if (getArguments() != null) {
            Log.d(TAG, "onCreateView - getArguments not null");
            mStep = getArguments().getParcelable(getString(R.string.key_step));
            mCurrentStep = getArguments().getInt(getString(R.string.key_current_step));
            mStepSize = getArguments().getInt(getString(R.string.key_step_size));
        }

        mTvShortDesc.setText(mStep.getShortDescription());
        mTvDesc.setText(mStep.getDescription());

        if (mCurrentStep == 0) {
            mBtnPrev.setVisibility(View.GONE);
        } else if (mCurrentStep == mStepSize - 1) {
            mBtnNext.setVisibility(View.GONE);
        }

        showThumbnail(mStep.getThumbnailURL());

        return root;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mListener = (StepListener) context;
    }

    @Override
    public void onStart() {
        super.onStart();
        createVideo(mStep.getVideoURL());
    }

    @Override
    public void onStop() {
        super.onStop();
        if (mPlayer != null) {
            updateResumePosition();
            mPlayer.release();
            mPlayer = null;
            mTrackSelector = null;
        }
    }

    private void updateResumePosition() {
        resumeWindow = mPlayer.getCurrentWindowIndex();
        resumePosition = mPlayer.isCurrentWindowSeekable() ? Math.max(0, mPlayer.getCurrentPosition()) : C.TIME_UNSET;
    }

    private void showThumbnail(String thumbnailURL) {
        if (TextUtils.isEmpty(thumbnailURL) || thumbnailURL.endsWith(".mp4")) {
            return;
        }

        Glide.with(this).load(thumbnailURL).into(mIvThumbnail);
        mIvThumbnail.setVisibility(View.VISIBLE);
    }

    private void createVideo(String videoUrl) {
        if (TextUtils.isEmpty(videoUrl)) {
            return;
        }

        BandwidthMeter bandwidthMeter = new DefaultBandwidthMeter();
        TrackSelection.Factory videoTrackSelectionFactory = new AdaptiveTrackSelection.Factory(bandwidthMeter);
        mTrackSelector = new DefaultTrackSelector(videoTrackSelectionFactory);

        mPlayer = ExoPlayerFactory.newSimpleInstance(getContext(), mTrackSelector);

        mPlayerView.setPlayer(mPlayer);

        DataSource.Factory dataSourceFactory = new DefaultDataSourceFactory(getContext(), Util.getUserAgent(getContext(), "yourApplicationName"), new DefaultBandwidthMeter());
        videoSource = new ExtractorMediaSource(Uri.parse(videoUrl), dataSourceFactory, new DefaultExtractorsFactory(), null, null);
        boolean haveResumePosition = resumeWindow != C.INDEX_UNSET;
        if (haveResumePosition) {
            mPlayer.seekTo(resumeWindow, resumePosition);
        }
        mPlayer.prepare(videoSource, !haveResumePosition, false);

        mPlayerView.setVisibility(View.VISIBLE);
    }

    @OnClick(R.id.btn_prev)
    public void onClickPrev() {
        mListener.onStepSelected(mCurrentStep - 1);
    }

    @OnClick(R.id.btn_next)
    public void onClickNext() {
        mListener.onStepSelected(mCurrentStep + 1);
    }

}
