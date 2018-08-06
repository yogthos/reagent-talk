(ns ^:figwheel-no-load reagent-talk.dev
  (:require
    [reagent-talk.core :as core]
    [devtools.core :as devtools]))


(enable-console-print!)

(devtools/install!)

(core/init!)
