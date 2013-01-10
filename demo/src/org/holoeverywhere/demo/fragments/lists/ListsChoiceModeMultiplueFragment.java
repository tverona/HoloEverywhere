
package org.holoeverywhere.demo.fragments.lists;

import org.holoeverywhere.ArrayAdapter;
import org.holoeverywhere.app.ListFragment;
import org.holoeverywhere.demo.R;
import org.holoeverywhere.widget.ListView;

import android.view.View;
import android.widget.AbsListView;

import com.actionbarsherlock.view.Menu;
import com.actionbarsherlock.view.MenuInflater;
import com.actionbarsherlock.view.MenuItem;

public class ListsChoiceModeMultiplueFragment extends ListFragment {
    private ListView mList;

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.lists_choice_mode_mulitplue, menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem menuItem) {
        switch (menuItem.getItemId()) {
            case R.id.inverse:
                final int count = mList.getCount();
                for (int i = 0; i < count; i++) {
                    mList.setItemChecked(i, !mList.isItemChecked(i));
                }
                break;
            default:
                return super.onOptionsItemSelected(menuItem);
        }
        return true;
    }

    @Override
    public void onViewCreated(View view) {
        super.onViewCreated(view);
        setHasOptionsMenu(true);
        mList = getListView();
        mList.setChoiceMode(AbsListView.CHOICE_MODE_MULTIPLE);
        setListAdapter(ArrayAdapter.createFromResource(getActivity(), R.array.adjectives,
                R.layout.simple_list_item_multiple_choice));
    }
}
