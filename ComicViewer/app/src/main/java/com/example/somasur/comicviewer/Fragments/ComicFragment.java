package com.example.somasur.comicviewer.Fragments;

import android.app.AlertDialog;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.somasur.comicviewer.Comic;
import com.example.somasur.comicviewer.ComicsPagerAdapter;
import com.example.somasur.comicviewer.MainActivity;
import com.example.somasur.comicviewer.QustomDialogBuilder;
import com.example.somasur.comicviewer.R;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.chrisbanes.photoview.PhotoView;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;


/**
 * A placeholder fragment containing a simple view.
 */
public class ComicFragment extends Fragment {
    /**
     * The fragment argument representing the section number for this
     * fragment.
     */
    private static final String ARG_COMIC_WRAPPER = "comic_wrapper";
    private static final String ARG_FRAG_POS = "fragment_position";
    private static Comic mComic;
    private int position;
    private TextView mTextView;
    private ImageView mImageView;
    private PhotoView mPhotoView;

    public ComicFragment() {
    }

    /**
     * Returns a new instance of this fragment for the given section
     * number.
     */
    public static ComicFragment newInstance(int index) {
        ComicFragment fragment = new ComicFragment();
        Bundle args = new Bundle();
        Comic comic = new Comic();
        args.putParcelable(ARG_COMIC_WRAPPER, comic);
        args.putInt(ARG_FRAG_POS, index);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        setHasOptionsMenu(true);
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mComic = getArguments().getParcelable(ARG_COMIC_WRAPPER);
            String urlString1 = String.format("https://xkcd.com/%d/info.0.json", mComic.getIssue());
            new GetComicTask().execute(urlString1);
            position = getArguments().getInt(ARG_FRAG_POS);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_main, container, false);
        View view = rootView.findViewById(R.id.relative_layout);
        if (getArguments() != null) {
            position = getArguments().getInt(ARG_FRAG_POS);
        }
        switch ((position - 1) % 4) {
            case 0:
                view.setBackgroundColor(getResources().getColor(android.R.color.holo_green_light));
                break;
            case 1:
                view.setBackgroundColor(getResources().getColor(android.R.color.holo_blue_light));
                break;
            case 2:
                view.setBackgroundColor(getResources().getColor(android.R.color.holo_orange_light));
                break;
            case 3:
                view.setBackgroundColor(getResources().getColor(android.R.color.holo_red_light));
                break;
        }
        mTextView = rootView.findViewById(R.id.section_label);
        mPhotoView = rootView.findViewById(R.id.comic_image);
        return rootView;
    }

    public static String getPageTitle() {
        String title = "Issue: " + title();
        return title;
    }

    public void onComicLoaded(Comic comic) {
        Log.d("COMIC ", "Comic Object\n" + comic);
        mComic.setComic(comic);
        mTextView.setText(comic.getSafe_title());
    }

    public void onImageLoaded(Bitmap bitmap) {
        Bitmap mBitmap = bitmap;
        mPhotoView.setImageBitmap(mBitmap);
    }

    public class GetComicTask extends AsyncTask<String, Void, Comic> {

        @Override
        protected Comic doInBackground(String... urlstrings) {
            String urlString = urlstrings[0];
            Comic comic = null;
            try {
                comic = new ObjectMapper().readValue(new URL(urlString), Comic.class);
                urlString = comic.getImg();
                InputStream in = new java.net.URL(urlString).openStream();
                Bitmap bitmap = BitmapFactory.decodeStream(in);
                comic.setImage(bitmap);
            } catch (IOException e) {
                Log.d("ERROR: ", e.toString());
            }
            return comic;
        }

        @Override
        protected void onPostExecute(Comic comic) {
            super.onPostExecute(comic);
            onComicLoaded(comic);
            ((MainActivity) getActivity()).dataChanged();
            onImageLoaded(comic.getImage());
        }
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        } else if (id == R.id.action_info) {
            alertDialogforMouseOvetText();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void alertDialogforMouseOvetText() {
        String issueNumber = "Mouseover text for: " + mComic.getNum() + "";
        QustomDialogBuilder qustomDialogBuilder = new QustomDialogBuilder(getContext()).
                setTitle(issueNumber).
                setTitleColor("#ff33b5e5").
                setDividerColor("#ff33b5e5").
                setMessage(mComic.getAlt());

        qustomDialogBuilder.show();
    }

    public static String title() {
        if (mComic != null) {
            return mComic.getNum() + "";
        } else return "Issue: ";
    }
}
