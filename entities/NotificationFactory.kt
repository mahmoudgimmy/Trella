package com.cargood.factories

import com.elm.entities.notification.NotificationItem
import sun.text.normalizer.UTF16.append

class NotificationFactory:GeneralFactory<List<NotificationItem>,List<NotificationItem>>() {
    override fun convert(data: List<NotificationItem>): List<NotificationItem> {
     return emptyList()
    }


}