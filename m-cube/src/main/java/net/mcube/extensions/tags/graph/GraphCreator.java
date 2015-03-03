/**
 * Copyright (C) 2003-2008 Daniele Dellafiore <daniele.dellafiore@gmail.com>
 * 
 * This program is free software; you can redistribute it and/or
 * modify it under the terms of the GNU General Public License
 * as published by the Free Software Foundation; either version 2
 * of the License, or (at your option) any later version.
 * 
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public License
 * along with this program; if not, write to the Free Software
 * Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston, MA  02110-1301, USA.
 */

package net.mcube.extensions.tags.graph;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import della.application.datatypes.Item;
import della.application.datatypes.ObservableItem;

public class GraphCreator {
	
	public List createTagsAssociations(List queryList) {
//		sulla query list creo le associazioni: 
//			1. quelle 60s - 1961
//			2. quelle in base alle corrispondenze dei tag
//	
//		ma cosa faccio, taggo 60s tutte le canzoni del 60? e il tag resta?
//		    S�, lo faccio perch� mi serve avere una categorizzazione di prova.
//			Nell'applicazione vera, baster� farlo fare all'utente di sponte sua. 
//			Magari colgo per fare il wizard: 
//				. dai un tag ai file che stai aggiungendo
//				. spunta se vuoi taggare con 60s e amici
//			
//		ogni tag creo un elemento Item con mainAttribute Tag, come quello che gi� faccio sotto
//		magari estendo item e invece di avere "child" ha "link"
//		year ha link 60s che a sua volta ha 1961
//		poi salvo i tag che compaiono assieme: per questo servono tecniche improvvisate o di AI, non lo 
//			faccio sto giro, lo accenno nel progetto di IG, per il quale baster� avere gli anni a titolo esemplificativo
//			
//		la update nel listChanged probabilmente dovr� essere chiamata passandogli una nuova lista che creo qui dentro, nella 
//		migliore delle ipotesi posso non toccare il resto almeno per questa particolare cosa
//		dovr� toccarlo per far caricare da year la lista dei suoi link e spostare in background gli altri stronzi,
//		cio� i label attuali.
//		
//		I label mostrati sono quelli con il voto pi� alto
//		il voto dipende da frequenza/vecchiaia di aggiornamento del tag 
//		Come tengo la vecchiaia del tag? serve un file esterno al DB, e questo � male...
//		O forse viene bene anche solo calcolando in base alla vecchiaia di quando suoni le canzoni in relazione al tag, e basta aggiungere un LAST_PLAYED
		
		List workingList = new ArrayList(queryList);
		List graphList = new ArrayList();
		for (Iterator it = workingList.iterator(); it.hasNext();) {
			ObservableItem item = (ObservableItem) it.next();
			graphList.add(createGraphElement(item, workingList));
//			it.remove();
		}
		return graphList;
	}

	private GraphElement createGraphElement(ObservableItem item, List workingList) {
		GraphElement graphElement = GraphElement.newGraphElement(item);
		for (Iterator it = workingList.iterator(); it.hasNext();) {
			ObservableItem fighterItem = (ObservableItem) it.next();
			if (match(item, fighterItem))
				graphElement.addLink(GraphElement.newGraphElement(fighterItem));
		}
		return graphElement;
	}

	private boolean match(Item item, Item fighterItem) {
		for (Iterator it = item.childIterator(); it.hasNext();) {
			Item child = (Item) it.next();
			if (fighterItem.hasChild(child))
				return true;						
		}
		return false;
	}

}
