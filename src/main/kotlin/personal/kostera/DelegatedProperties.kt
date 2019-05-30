package personal.kostera

import kotlin.properties.Delegates
import kotlin.reflect.KProperty

class ClassWithDelegatedProperties {

    // Custom Delegate
    var stringCustomDelegate: String by CustomStringDelegate()

    // Lazy delegates
    val stringLazyDelegate: String by lazy {
        stringCustomDelegate
    }

    // Observable delegate
    var stringObservableDelegate: String by Delegates.observable("Empty Value") { property, old, new ->
        lastValue = old
        newValue = new
    }
    var lastValue: String = ""
    var newValue: String = ""

    // Vetoable delegate
    var stringVetoableDelegate: String by Delegates.vetoable("") { property, old, new ->
        old.length < new.length
    }

    // notNull delegate
    var stringNotNullDelegate: String by Delegates.notNull()
}

class MapDelegatedClass(map: Map<String, Any?>) {
    val name: String by map
    val age: Int by map
}

class CustomStringDelegate {

    private var value: String? = null

    operator fun getValue(thisRef: Any?, prop: KProperty<*>): String {
        return value ?: "Value was not assigned yet"
    }

    operator fun setValue(thisRef: Any?, prop: KProperty<*>, value: String) {
        if (this.value == null) {
            this.value = value
        } else {
            this.value = "Zonk!"
        }
    }
}
