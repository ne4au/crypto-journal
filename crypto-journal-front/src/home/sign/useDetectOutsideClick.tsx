import { useState, useEffect, RefObject, Dispatch, SetStateAction } from 'react';
import { identity, pipe } from 'fp-ts/lib/function';
import { array as A } from 'fp-ts';


export const useDetectOutsideClick = (
    elements: Array<RefObject<HTMLElement>>,
    initialState: boolean): [boolean, Dispatch<SetStateAction<boolean>>] => {
    const [isActive, setIsActive]: [boolean, Dispatch<SetStateAction<boolean>>] = useState<boolean>(initialState);

    useEffect(() => {
        const pageClickEvent = <K extends keyof WindowEventMap>(e: WindowEventMap[K]) => {
            const isOutsideAllBlocks = elements
                    .map(elem => elem.current)
                    .map(current => current && !current.contains(e.target as Node))
                    .every(identity);
            if (isOutsideAllBlocks) {
                setIsActive(false);
            }
        }

        if (isActive) {
            window.addEventListener('click', pageClickEvent);
        }

        return () => {
            window.removeEventListener('click', pageClickEvent);
        }
    }, [isActive, ...elements]);
    return [isActive, setIsActive];
};