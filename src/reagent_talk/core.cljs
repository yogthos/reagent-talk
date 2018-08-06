(ns reagent-talk.core
  (:require
    [reagent.core :as r]
    [cljsjs.semantic-ui-react :as ui]))

(defonce state (r/atom {:count 0}))

(defn render-text [converter]
  (fn [component]
    (let [dom-node (r/dom-node component)]
      (set! (.-innerHTML dom-node) (.makeHtml converter (:text @state))))))

(defn preview []
  (let [converter (js/showdown.Converter.)]
    (r/create-class
      {:component-did-mount  (render-text converter)
       :component-did-update (render-text converter)
       :render               (fn [_] [:div])})))

(defn editor []
  [:> ui/Grid
   [:> ui/Grid.Row
    {:columns 2}
    [:> ui/Grid.Column
     [:textarea
      {:on-change #(swap! state assoc :text (-> % .-target .-value))
       :value     (:text @state)}]]
    [:> ui/Grid.Column
     [preview (:text @state)]]]])

(defn increment-button []
  [:> ui/Button
   {:primary  true
    :on-click #(swap! state update :count inc)}
   "increment"])

(defn display-count []
  [:> ui/Label (:count @state)])

(defn home-page []
  [:div
   [editor]
   [increment-button]
   [display-count]])

;; -------------------------
;; Initialize app

(defn mount-root []
  (r/render [home-page] (.getElementById js/document "app")))

(defn init! []
  (mount-root))
