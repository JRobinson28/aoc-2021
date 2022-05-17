(ns aoc-2021.day-08
  (:require [aoc-2021.common :refer :all]
            [clojure.set :as set]
            [clojure.string :as s]))

(defn- format-line
  [line]
  (let [data (-> line
                 (s/trim)
                 (s/split #" \| "))]
    {:inputs (s/split (first data) #" ")
     :outputs (s/split (second data) #" ")}))

(defn- format-data
  [filename]
  (->> (s/split (parse-input filename) #"\n")
       (map format-line)))

(defn- count-unique-outputs
  [filename]
  (->> (format-data filename)
       (mapcat :outputs)
       (filter #(#{2 4 3 7} (count %)))
       count))

(defn- solve-line
  [{:keys [inputs outputs] :as line}]
  (let [input-set (map set inputs)
        [one seven four & nums] (sort-by count input-set)
        [has235 has069 [eight]] (partition-all 3 nums)
        intersects-with-4 #(count (set/intersection four %))
        difference-from-1 #(count (set/difference one %))
        [two three five] (sort-by (juxt intersects-with-4 difference-from-1) has235)
        [zero six nine] (sort-by (juxt intersects-with-4 difference-from-1) has069)
        value-mapping {zero 0 one 1 two 2 three 3 four 4 five 5 six 6 seven 7 eight 8 nine 9}]
    (->> (map (comp value-mapping set) outputs)
         s/join
         parse-long)))

(defn- sum-outputs
  [filename]
  (->> (format-data filename)
       (map solve-line)
       (reduce +)))

;; Part-1
(defn part-1
  [filename]
  (count-unique-outputs filename))

(defn part-2
  [filename]
  (sum-outputs filename))
