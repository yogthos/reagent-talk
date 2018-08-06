(ns reagent-talk.prod
  (:require
    [reagent-talk.core :as core]))

;;ignore println statements in prod
(set! *print-fn* (fn [& _]))

(core/init!)
