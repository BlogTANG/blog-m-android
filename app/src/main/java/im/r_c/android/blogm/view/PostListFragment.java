package im.r_c.android.blogm.view;


import android.content.Context;
import android.databinding.DataBindingUtil;
import android.net.Uri;
import android.os.Bundle;
import android.support.customtabs.CustomTabsIntent;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import im.r_c.android.blogm.App;
import im.r_c.android.blogm.R;
import im.r_c.android.blogm.data.model.Post;
import im.r_c.android.blogm.data.model.PostList;
import im.r_c.android.blogm.data.source.Repository;
import im.r_c.android.blogm.databinding.FragmentPostListBinding;
import im.r_c.android.blogm.databinding.PostListItemBinding;
import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;


/**
 * BlogM
 * Created by richard on 8/12/16.
 */

public class PostListFragment extends Fragment implements SwipeRefreshLayout.OnRefreshListener {

    public static PostListFragment newInstance() {

        Bundle args = new Bundle();

        PostListFragment fragment = new PostListFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Inject
    Repository mRepository;

    private FragmentPostListBinding mBinding;
    private List<Post> mPostList;

    public PostListFragment() {
        App.getDataSourceComponent().inject(this);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_post_list, container, false);

        mBinding.rvPostList.setLayoutManager(new LinearLayoutManager(getContext()));
        mBinding.rvPostList.addItemDecoration(new DividerItemDecoration(getContext(), DividerItemDecoration.VERTICAL_LIST));

        mPostList = new ArrayList<>();
        PostListAdapter adapter = new PostListAdapter(getContext(), mPostList);
        adapter.setOnItemClickListener((v, position) -> {
            if (position >= mPostList.size()) {
                return;
            }
            String url = getString(R.string.base_url) + mPostList.get(position).getUrl();
            CustomTabsIntent.Builder builder = new CustomTabsIntent.Builder();
            CustomTabsIntent customTabsIntent = builder.build();
            customTabsIntent.launchUrl(getActivity(), Uri.parse(url));
        });
        mBinding.rvPostList.setAdapter(adapter);

        mBinding.srlPostListSwipeRefresh.setOnRefreshListener(this);
        mBinding.srlPostListSwipeRefresh.setRefreshing(true);
        getPostList("/", true);

        return mBinding.getRoot();
    }

    private void getPostList(String relUrl, boolean localFirst) {
        Observable<PostList> observable = mRepository.getPostList(relUrl);

        if (localFirst) {
            observable = observable.first(postList -> postList != null);
        } else {
            observable = observable.last();
        }

        observable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(postList -> {
                    if (postList != null && postList.getEntries() != null && postList.getEntries().size() > 0) {
                        if ("/".equals(relUrl)) {
                            mPostList.clear();
                            mPostList.addAll(postList.getEntries());
                            mBinding.rvPostList.getAdapter().notifyDataSetChanged();
                        } else {
                            int changeStart = mPostList.size();
                            mPostList.addAll(postList.getEntries());
                            mBinding.rvPostList.getAdapter().notifyItemRangeChanged(changeStart, postList.getEntries().size());
                        }

                        if (postList.hasOlder()) {
                            getPostList(postList.getOlderUrl(), localFirst);
                        } else {
                            mBinding.srlPostListSwipeRefresh.setRefreshing(false);
                        }
                    } else {
                        mBinding.srlPostListSwipeRefresh.setRefreshing(false);
                    }
                });
    }

    @Override
    public void onRefresh() {
        getPostList("/", false);
    }

    private static class PostListItemViewHolder extends RecyclerView.ViewHolder {

        public static PostListItemViewHolder create(LayoutInflater inflater, ViewGroup parent) {
            return new PostListItemViewHolder(DataBindingUtil.inflate(inflater, R.layout.post_list_item, parent, false));
        }

        private PostListItemBinding mBinding;

        private PostListItemViewHolder(PostListItemBinding binding) {
            super(binding.getRoot());
            mBinding = binding;
        }

        public void bindTo(Post post) {
            mBinding.setPost(post);
            mBinding.executePendingBindings();
        }
    }

    private static class PostListAdapter extends RecyclerView.Adapter<PostListItemViewHolder> {

        private List<Post> mPostList;
        private LayoutInflater mLayoutInflater;
        private OnItemClickListener mOnItemClickListener;

        public PostListAdapter(Context context, List<Post> postList) {
            mLayoutInflater = LayoutInflater.from(context);
            mPostList = postList;
        }

        public void setOnItemClickListener(OnItemClickListener listener) {
            mOnItemClickListener = listener;
        }

        @Override
        public PostListItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            return PostListItemViewHolder.create(mLayoutInflater, parent);
        }

        @Override
        public void onBindViewHolder(PostListItemViewHolder holder, int position) {
            holder.bindTo(mPostList.get(position));
            holder.itemView.setOnClickListener(v -> {
                if (mOnItemClickListener != null) {
                    mOnItemClickListener.onItemClick(v, holder.getLayoutPosition());
                }
            });
        }

        @Override
        public int getItemCount() {
            return mPostList.size();
        }

        public interface OnItemClickListener {
            void onItemClick(View v, int position);
        }
    }
}
