(ns ^:figwheel-always tester.ui.core
  (:require [figwheel.client :include-macros true]
            [cljs.core.async :as async]
            [clojure.browser.dom]
            [om.core :as om :include-macros true]
            [om.dom :as dom :include-macros true]))

(enable-console-print!)

(declare on-js-reload)
(declare app-state)

(figwheel.client/watch-and-reload
  :websocket-url "ws://localhost:3449/figwheel-ws"
  :jsload-callback on-js-reload)

(defn on-js-reload []
  (om/transact! (om/root-cursor app-state) [:__figwheel_counter] inc))

(defonce app-state (atom {}))

(om/root
  (fn [data _owner]
    (reify om/IRender
      (render [_]
        (dom/h1 (clj->js {})
                 "hi there"))))
  app-state
  {:target (.getElementById js/document "app")})
