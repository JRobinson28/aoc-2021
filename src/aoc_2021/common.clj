(ns aoc-2021.common
  (:require [clojure.java.io :as io]
            [clojure.string :as s]))

(defn parse-input
  [filename]
  (-> filename io/resource slurp))

(defn parse-int-radix
  ([s]
   (parse-int-radix s 10))
  ([s radix]
   (try (Integer/parseInt s radix)
        (catch Exception e nil))))
