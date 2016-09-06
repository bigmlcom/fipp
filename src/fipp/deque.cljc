(ns fipp.deque
  "Double-sided queue not built on rrb vectors, because it doesn't compile
   with current cljs."
  (:refer-clojure :exclude [empty concat])
  #?(:clj (:require [clojure.core.rrb-vector :as rrb])))

(def create vector)

(def empty [])

(defn popl [v]
  (subvec v 1))

(def conjr (fnil conj empty))

(defn- slow-catvec [& args]
  (vec (apply clojure.core/concat args)))

(def concat #?(:clj rrb/catvec :cljs slow-catvec))

(defn conjlr [l deque r]
  (concat [l] deque [r]))
