package id.ergun.mymoviedb.ui.module.favorite.stackWidget

import android.content.Intent
import android.widget.RemoteViewsService

/**
 * Created by alfacart on 30/12/19.
 */
class FavoriteStackWidgetService : RemoteViewsService() {
    override fun onGetViewFactory(intent: Intent): RemoteViewsFactory =
        FavoriteStackRemoteViewsFactory(this.applicationContext, intent)
}