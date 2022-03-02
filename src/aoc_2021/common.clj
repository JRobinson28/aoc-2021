(ns aoc-2021.common
  (:require [clojure.java.io :as io]))

(defn parse-input
  [filename]
  (-> filename io/resource io/reader line-seq))
