(ns vim-sexp-demo.core
  (:require [clojure.java.io :as io]
            [clojure.data.csv :as csv]
            [clojure.string :as string]
            [schema.core :as s]
            ))

(defn read-csv
  [reader]
  (let [[header & contents] (csv/read-csv reader)
        header-keys         (map keyword header)]
    (map-indexed (fn [idx line]
                   (with-meta (zipmap header-keys line)
                              {:address (inc idx)}))
                 contents)))

(def contracts-data
  (-> (io/resource "uk_gov_contracts.csv")
      (io/reader :encoding "UTF-8")
      read-csv))

(comment
  (->> contracts-data
       first
       keys)

  (count contracts-data)
  )
